(ns workout-timer.ops.workout.events
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [com.rpl.specter :as s]
            [workout-timer.ops.workout.util :as util]))

(rf/reg-event-fx
  :workout/start-workout
  rf/trim-v
  (fn [cofx [workout]]
    {:dispatch-n [[:workout/set-workout workout]
                  [::nav/navigate "WorkoutMain"]]}))

(defn- get-exercises [db workout]
  "Get the first 10 random exercises - exercises can repeat!"
  (let [ex-keys (keys (:exercises db))
        num-ex (util/number-of-exercises workout) ]
    (repeatedly num-ex #(rand-nth ex-keys))))

;; What is happening here???
(defn- get-ex-timing [idx ex sec-per-ex init-rest final-rest num-ex]
  (let [ex-rest (+ init-rest (* (/ idx num-ex) (- final-rest init-rest)))]
    {:id ex
     :rest ex-rest
     :active (- sec-per-ex ex-rest)
     :elapsed 0}))

;; Eesh. Is there a better way to do this?
(defn- build-ex-timing [workout exercises]
  (let [sec-per-ex (:seconds-per-exercise workout)
        init-rest (:initial-rest workout)
        final-rest (:final-rest workout)
        num-exercises (count exercises)]

    (map-indexed
      (fn [idx ex] (get-ex-timing idx
                                  ex
                                  sec-per-ex
                                  init-rest
                                  final-rest
                                  num-exercises)) exercises)))

(rf/reg-event-db
  :workout/set-workout
  rf/trim-v
  (fn [db [workout]]
    (let [workout-id (:id workout)
          exercise-list (get-exercises db workout)
          exercises (build-ex-timing workout exercise-list)]
      (assoc db :current-workout {:id workout-id
                                  :exercises (into [] exercises)
                                  :running false
                                  :complete false    ;; If we're done the workout
                                  :resting false     ;; If we're in a rest period
                                  :exercise-idx 0    ;; Current exercise
                                  :total-elapsed 0   ;; Overall workout timing
                                  ;:current-elapsed 0 ;; Current exercise/rest timing
                                  }))))


;; Toggle whether or not this workout is running/paused
(rf/reg-event-fx
  :workout/toggle-running
  rf/trim-v
  (fn [cofx [_]]
    (let [db (:db cofx)
          run-key [:current-workout :running]
          is-running (get-in db run-key)
          timer-evt (if is-running :workout/stop-timer :workout/start-timer)]

      {
       :db (assoc-in db run-key (not is-running))
       :dispatch [timer-evt]
       }

      )))

(def workout-timer-id :workout-timer-id)

(rf/reg-event-fx
  :workout/start-timer
  rf/trim-v
  (fn [cofx [_]]
    {:dispatch-interval {:dispatch [:workout/increment-timer]
                         :id workout-timer-id
                         ;:ms 1000}}))
                         ;:ms 100}}))
                         :ms 25}}))

(rf/reg-event-fx
  :workout/stop-timer
  rf/trim-v
  (fn [cofx [_]]
    {:clear-interval {:id workout-timer-id}}))


(defn- inc-total-elapsed [db]
  (update-in db [:current-workout :total-elapsed] inc))

(defn- inc-curr-ex-elapsed [db]
  (let [curr-state (util/current-workout-state db)
        idx (:exercise-idx curr-state)]
    (update-in db [:current-workout :exercises idx :elapsed] inc)))

(defn- check-for-next-ex [db]
  (let [workout (util/get-current-workout db)
        curr-state (util/current-workout-state db)
        idx (:exercise-idx curr-state)
        ex-elapsed (get-in curr-state [:exercises idx :elapsed])
        sec-per-ex (:seconds-per-exercise workout)]

    ;(println (str "EX ELAPSED: " ex-elapsed))
    ;(println (str "SEC PER EX: " sec-per-ex))

    (if (> ex-elapsed sec-per-ex)
      (update-in db [:current-workout :exercise-idx] inc)
      db)))

;; If the exercise idx points to nil, set the complete flag - if we
;; are running and complete, set running to false
(defn- check-for-complete [db]
  (let [curr-state (util/current-workout-state db)
        idx (:exercise-idx curr-state)
        curr-ex (get-in curr-state [:exercises idx])
        complete (not (boolean curr-ex))
        running (-> curr-state
                    (:running)
                    (and (not complete)))]
    (-> db
        (assoc-in [:current-workout :complete] complete)
        (assoc-in [:current-workout :running] running))))

;; When we get to the next tick in the timer:
;; 1) Increment :total-elapsed (overall workout time)
;; 2) Increment :current-elapsed (current exercise time)
;; 3) If we hit the end of the exercise, move to the new
;;    exercise, and re-set the current exercise timer
;; 4) If there are no more exercises, set the 'complete'
;;    flag to true, and stop the timer.
(rf/reg-event-fx
  :workout/increment-timer
  rf/trim-v

  (fn [cofx [_]]

    (let [db (-> (:db cofx)
                 (inc-total-elapsed)
                 (inc-curr-ex-elapsed)
                 (check-for-next-ex)
                 (check-for-complete))
          complete (get-in db [:current-workout :complete])]

      (if complete
        {:db db :dispatch [:workout/stop-timer]}
        {:db db}))))

(rf/reg-event-fx
  :workout/delete-workout
  rf/trim-v
  (fn [cofx [[workout]]] ;; WTF? This is stupid

    (let [db (:db cofx)
          workout-id (:id workout)
          new-db (s/setval [:workouts workout-id] s/NONE db)]

      ;(println (str "WORKOUT ID: " workout-id))
      ;(println "NEW DB")
      ;(println new-db)

      (when workout-id
        {:db new-db
         :dispatch [:store-db]}))))
