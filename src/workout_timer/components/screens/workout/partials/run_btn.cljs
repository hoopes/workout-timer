(ns workout-timer.components.screens.workout.partials.run-btn
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(defn- pause-btn []
  [rn/Text {:style {:text-align "center"}}
   "PAUSE"])

(defn- start-btn []
  [rn/Text {:style {:text-align "center"}}
   "START"])

(def main-panel
  (fn []
    (let [is-running (rf/subscribe [:workout/running])]

      [rn/TouchableHighlight
       {:on-press #(rf/dispatch [:workout/toggle-running])}
       [rn/View {:style {:border-color "black"
                         :border-top-width 2
                         :padding-top 5
                         }}
        (if @is-running [pause-btn] [start-btn])]])))
