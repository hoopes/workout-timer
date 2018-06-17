(ns workout-timer.ops.workout.subs
  (:require [re-frame.core :as rf]
            [workout-timer.ops.workout.util :as util]))


;; Return the current workout, or nil if there is
;; none selected. The workouts are held as a map
;; of id -> map in the :workouts key of the db. Note
;; that we hold the
(rf/reg-sub
  :workout/current-workout
  (fn [db _]
    (util/get-current-workout db)))

(rf/reg-sub
  :workout/exercise-list
  (fn [db _]
    ;(println (str "DB: " db))
    (let [ex-keys (get-in db [:current-workout :exercises])]

      ;; Now that we have the keys in order for this workout,
      ;; return their actual values in the same order
      (map #(get-in db [:exercises %]) ex-keys))))

;; Return the time in seconds of the entire workout. This is
;; held in the workouts in the :total-length in minutes.
(rf/reg-sub
  :workout/overall-time
  (fn [db _]
    (let [workout (util/get-current-workout db)]
      (* (:total-length workout) 60))))

;; Return overall time remaining in the workout - this
;; should return the time in seconds as an int
(rf/reg-sub
  :workout/overall-time-remaining
  (fn [db _]
    (let [workout (util/get-current-workout db)
          total-time (util/total-workout-time workout)
          curr-state (util/current-workout-state db)
          elapsed (:total-elapsed curr-state) ]
      (- total-time elapsed))))

;; Return a list of workouts. Move this to a better location.
(rf/reg-sub
  :workout/ordered-workout-list
  (fn [db _]
    (let [workout-map (get db :workouts {})
          workout-list (vals workout-map)]
      ;; FIXME - do the order here? By Title?
      workout-list)))

(rf/reg-sub
  :workout/running
  (fn [db _]
    (get-in db [:current-workout :running])))
