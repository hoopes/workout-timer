(ns workout-timer.ops.workout.subs
  (:require [re-frame.core :as rf]))

;; Get the workout information, including the
;; timing information and other metadata like
;; title and whatnot (maybe list of allowed exercises?)
(defn- get-current-workout [db]
  (let [curr-id (get-in db [:current-workout :id])
          workouts (:workouts db)]
      (when curr-id
        (get workouts curr-id))))

;; Return the state of the current workout
;(defn- current-workout-state [db] )

;; Return the current workout, or nil if there is
;; none selected. The workouts are held as a map
;; of id -> map in the :workouts key of the db. Note
;; that we hold the
(rf/reg-sub
  :workout/current-workout
  (fn [db _]
    (get-current-workout db)))

(rf/reg-sub
  :workout/exercise-list
  (fn [db _]
    ;(println (str "DB: " db))
    (let [ex-keys (get-in db [:current-workout :exercises])]

      ;; Now that we have the keys in order for this workout,
      ;; return their actual values in the same order
      (map #(get-in db [:exercises %]) ex-keys))))


(rf/reg-sub
  :workout/exercise-time-remaining
  (fn [db _]
    ))

;;
(rf/reg-sub
  :ordered-workout-list
  (fn [db _]
    (let [workout-map (get db :workouts {})
          workout-list (vals workout-map)]
      ;; FIXME - do the order here? By Title?
      workout-list)))
