(ns workout-timer.components.screens.config.workout.util.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :edit/open-picker
  (fn [db _]
    (get-in db [:edit-workout :picker])))

(rf/reg-sub
  :edit/workout
  (fn [db _]
    (get-in db [:edit-workout :workout])))
