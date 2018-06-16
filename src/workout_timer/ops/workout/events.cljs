(ns workout-timer.ops.workout.events
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [com.rpl.specter :as s]))

(rf/reg-event-fx
  :workout/start-workout
  rf/trim-v
  (fn [cofx [workout]]
    {:dispatch-n [[:workout/set-workout workout]
                  [::nav/navigate "WorkoutMain"]]}))

(defn- get-exercises [db]
  "Get the first 10 random exercises - exercises can repeat!"
  (let [ex-keys (keys (:exercises db))]
    (repeatedly 10 #(rand-nth ex-keys))))

(rf/reg-event-db
  :workout/set-workout
  rf/trim-v
  (fn [db [workout]]
    (let [workout-id (:id workout)
          exercises (get-exercises db)]
    (assoc db :current-workout {:id workout-id
                                :exercises exercises
                                :paused false}))))


(rf/reg-event-db
  :workout/toggle-paused
  rf/trim-v
  (fn [db [_]]))

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
