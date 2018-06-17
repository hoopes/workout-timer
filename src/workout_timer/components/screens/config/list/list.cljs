(ns workout-timer.components.screens.config.list.list
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.config.list.item :as i]))

;; This is just a component that renders the list items in a loop. It
;; should be re-rendered whenever a workout changes (like is added or
;; deleted)

(defn workout-list-view []

  (fn []
    (let [workout-list (rf/subscribe [:workout/ordered-workout-list])]
      [rn/ScrollView {:style {:flex 1
                              :flex-direction "column"}}

       (for [workout @workout-list]
         ^{:key workout}
         [i/main workout])])))
