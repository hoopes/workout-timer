(ns workout-timer.subs.workout
  (:require [re-frame.core :as rf]))

;; Return the current workout, or nil if there is
;; none selected. The workouts are held as a map
;; of id -> map in the :workouts key of the db. Note
;; that we hold the
(rf/reg-sub
  :current-workout
  (fn [db _]
    (let [curr-id (:current-workout db)
          workouts (:workouts db)]
      (when curr-id
        (get workouts curr-id)))))

;;
(rf/reg-sub
  :ordered-workout-list
  (fn [db _]
    (let [workout-map (get db :workouts {})
          workout-list (vals workout-map)]
      ;; FIXME - do the order here? By Title?
      workout-list)))
