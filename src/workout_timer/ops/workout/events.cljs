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

(rf/reg-event-db
  :workout/set-workout
  rf/trim-v
  (fn [db [workout]]
    (let [workout-id (:id workout)
          exercises (get-exercises db workout)]
      (assoc db :current-workout {:id workout-id
                                  :exercises exercises
                                  :running false
                                  :total-elapsed 0   ;; Overall workout timing
                                  :current-elapsed 0 ;; Current exercise/rest timing
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
                         :ms 1000}}
    ))

(rf/reg-event-fx
  :workout/stop-timer
  rf/trim-v
  (fn [cofx [_]]
    (println "STOPPING TIMER")
    {:clear-interval {:id workout-timer-id}}
    ))

;; When we get to the next tick in the timer:
;; 1) Increment :total-elapsed (overall workout time)
;; 2) Increment :current-elapsed (current exercise time)
;; 3) If we hit the end of the exercise, move to the new
;;    exercise, and re-set the current exercise timer
(rf/reg-event-db
  :workout/increment-timer
  rf/trim-v
  (fn [db _]
    (let [new-db (-> db
                     (update-in [:current-workout :total-elapsed] inc))]
      new-db)))



(rf/reg-event-fx
  :workout/delete-workout
  rf/trim-v
  (fn [cofx [[workout]]] ;; WTF? This is stupid

    (let [db (:db cofx)
          workout-id (:id workout)
          new-db (s/setval [:workouts workout-id] s/NONE db)]

      (println (str "WORKOUT ID: " workout-id))
      (println "NEW DB")
      (println new-db)

      (when workout-id
        {:db new-db
         :dispatch [:store-db]}))))
