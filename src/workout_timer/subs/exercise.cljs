(ns workout-timer.subs.exercise
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  :current-exercise
  (fn [db _]
    (let [curr-id (:current-exercise db)])))
      ;exercises (:exercises]
      ;(if curr-id
        ;(first (filter (:exerci
                         ;(:greeting db))))))))
