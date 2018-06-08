(ns workout-timer.components.screens.config.workout.util.events
  (:require [re-frame.core :as rf]))

(defn- get-defaults []
  {:id (keyword (str (random-uuid)))
   :total-length 20
   :seconds-per-exercise 60
   :initial-rest 15
   :final-rest 45
   :exercises []})

(defn- set-workout-defaults [workout]
  (let [workout (if map? workout {})]
    (merge (get-defaults) workout)))

(rf/reg-event-db
  :edit/set-workout
  rf/trim-v
  (fn [db [workout]]
    (let [workout (set-workout-defaults workout)]
      (assoc-in db [:edit-workout :workout] workout))))

(rf/reg-event-db
  :edit/set-picker
  rf/trim-v
  (fn [db [picker]]
    (assoc-in db [:edit-workout :picker] picker)))

(rf/reg-event-db
  :edit/close-picker
  (fn [db []]
    (assoc-in db [:edit-workout :picker] nil)))

(rf/reg-event-db
  :edit/set-workout-attr
  rf/trim-v
  (fn [db [k v]]
    (println (str "Setting value for edit workout: " k " -> " v))
    (assoc-in db [:edit-workout :workout k] v)))


;; FIXME: Navigate!
;; FIXME: Save state to disk!
(rf/reg-event-db
  :edit/save-workout
  rf/trim-v
  (fn [db []]
    (let [workout (get-in db [:edit-workout :workout])
          workout (merge {:title "Untitled"} workout)
          workout-id (:id workout)]
      (-> db
          (assoc-in [:workouts workout-id] workout)
          (assoc :edit-workout nil)))))

;; FIXME: Navigate!
(rf/reg-event-db
  :edit/cancel-workout-edit
  rf/trim-v
  (fn [db []]
    (assoc db :edit-workout nil)))
