(ns workout-timer.events.workout
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [com.rpl.specter :as s]))


(rf/reg-event-fx
  :workout/start-workout
  rf/trim-v
  (fn [cofx [workout]]
    {:dispatch-n [[:workout/set-workout workout]
                  [::nav/navigate "WorkoutMain"]]}))

(rf/reg-event-db
  :workout/set-workout
  rf/trim-v
  (fn [db [workout]]
    (println "SETTING WORKOUT")
    (println workout)
    (assoc db :current-workout (:id workout))))

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
