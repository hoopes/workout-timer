(ns workout-timer.components.screens.workout.workout
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.workout.partials.progress :as progress]
            [workout-timer.components.screens.workout.partials.body :as body]
            [workout-timer.components.screens.workout.partials.run-btn :as run-btn]))

(defn- running-panel []
  [rn/View {:flex 12 :flex-direction "column"}
   [rn/View {:style {:flex 1 :border-color "black" :border-bottom-width 2}}
    [progress/panel]]
   [rn/View {:style {:flex 10}}
    [body/main-panel]]
   [rn/View {:style {:flex 1 :justify-content "center"}}
    [run-btn/main-panel]]])

(defn- complete-panel []
  [rn/View {:style {:flex 1
                    :align-items "center"
                    :justify-content "center"
                    :background-color "red"}}
   [rn/View
    [rn/Text "COMPLETE"]]])

(def workout-main-panel
  (fn []
    (let [is-complete (rf/subscribe [:workout/complete])]
      (if (not @is-complete)
        [running-panel]
        [complete-panel]))))
