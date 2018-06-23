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

(rf/reg-sub
  :workout/elapsed
  (fn [db _]
    (let [curr-state (util/current-workout-state db)]
      (:total-elapsed curr-state))))

;; Return a list of workouts. Move this to a better location.
(rf/reg-sub
  :workout/ordered-workout-list
  (fn [db _]
    (let [workout-map (get db :workouts {})
          workout-list (vals workout-map)]
      ;; FIXME - do the order here? By Title?
      workout-list)))

;; Return a boolean for if the workout is running or paused
(rf/reg-sub
  :workout/running
  (fn [db _]
    (get-in db [:current-workout :running])))

;; Return a boolean for if the workout is complete or not
(rf/reg-sub
  :workout/complete
  (fn [db _]
    (get-in db [:current-workout :complete])))

;; Return a boolean for if the exercise is active or resting
(rf/reg-sub
  :workout/exercise-active
  (fn [db _]
    (let [curr-state (util/current-workout-state db)
          curr-ex (util/curr-exercise curr-state)
          elapsed (:elapsed curr-ex)
          active-sec (:active curr-ex)]
      (< elapsed active-sec))))

;; Return the exercise definition for the current exercise - this
;; should include both the title and description, and the current
;; state of the exercise, including elapsed time, etc.
(rf/reg-sub
  :workout/current-exercise
  (fn [db _]
    (let [exercises (:exercises db)
          curr-state (util/current-workout-state db)
          curr-ex (util/curr-exercise curr-state)
          exercise-id (:id curr-ex)
          exercise-def (get-in db [:exercises exercise-id])]
      (merge exercise-def curr-ex))))

;; This will only return the definition of the next exercise,
;; which will contain title/desc. This CAN return nil if there
;; are no further exercises.
(rf/reg-sub
  :workout/next-exercise
  (fn [db _]
    (let [exercises (:exercises db)
          curr-state (util/current-workout-state db)
          next-idx (+ 1 (:exercise-idx curr-state))
          next-ex (get-in curr-state [:exercises next-idx])
          exercise-id (:id next-ex)]
      (get-in db [:exercises exercise-id]))))

;; Return the seconds remaining for the current exercise
(rf/reg-sub
  :workout/exercise-remaining
  (fn [db _]
    (let [workout (util/get-current-workout db)
          sec-per-ex (:seconds-per-exercise workout)
          curr-state (util/current-workout-state db)
          curr-ex (util/curr-exercise curr-state)]
      (- sec-per-ex (:elapsed curr-ex)) )))

;; Return the (1-based) position of what exercise you're on
(rf/reg-sub
  :workout/exercise-position
  (fn [db _]
    (let [curr-state (util/current-workout-state db)]
      (+ (:exercise-idx curr-state) 1))))

;; Return the total number of exercises in this workout
(rf/reg-sub
  :workout/num-exercises
  (fn [db _]
    (let [curr-state (util/current-workout-state db)]
      (count (:exercises curr-state)))))
