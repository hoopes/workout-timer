(ns workout-timer.components.screens.config.list.list
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.config.list.item :as i]))




(defn workout-list-view [props]

  (fn []
    (let [workout-list (rf/subscribe [:ordered-workout-list])]
      [rn/ScrollView {:style {:flex 1
                              :flex-direction "column"}}

       (for [workout @workout-list]
         ^{:key (:id workout)}
         [i/main props workout])])))
