(ns workout-timer.ops.workout.util)


;; Get the workout information, including the
;; timing information and other metadata like
;; title and whatnot (maybe list of allowed exercises?)
(defn get-current-workout [db]
  (let [curr-id (get-in db [:current-workout :id])
        workouts (:workouts db)]
    (when curr-id
      (get workouts curr-id))))

;; Return the state of the current workout
(defn current-workout-state [db]
  (:current-workout db))

;; We have the total length, and the seconds per exercise. If
;; we find the number of seconds / seconds per exercise, and
;; take the floor of that, we have the number of exercises
(defn number-of-exercises [workout]
  (let [total-sec (* (:total-length workout) 60)
        sec-per-ex (:seconds-per-exercise workout)]
    (int (/ total-sec sec-per-ex))))

;; Return the time in seconds of the entire workout. This is
;; held in the workouts in the :total-length in minutes.
(defn total-workout-time [workout]
  (let [sec-per-ex (:seconds-per-exercise workout)
        num-ex (number-of-exercises workout)]
    (* num-ex sec-per-ex)))


