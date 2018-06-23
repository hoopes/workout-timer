(ns workout-timer.components.screens.workout.partials.body
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(defn- workout-title []
  (fn []
    (let [workout (rf/subscribe [:workout/current-workout])]
      [rn/View
       [rn/Text {:style {:text-align "center"}}
        (:title @workout)]])))

(defn- bg-color []
  (let [is-active (rf/subscribe [:workout/exercise-active]) ]
    (if @is-active "#ffb5af" "#3db1ff")))

(defn- top-ex-panel []
  (fn []
    (let [exercise (rf/subscribe [:workout/current-exercise])
          exercise-rem (rf/subscribe [:workout/exercise-remaining])]
      [rn/View {:style {:flex 1
                        :flex-direction "row"
                        :background-color (bg-color)
                        :border-bottom-width 2
                        :border-color "black"
                        :align-items "center"}}
       [rn/Text {:style {:flex 1
                         :adjusts-font-size-to-fit true
                         :text-align "center"}}
        (:title @exercise)]
       [rn/Text {:style {:flex 1 :text-align "center"}}
        @exercise-rem]])))

(defn- bottom-ex-panel []
  (fn []
    (let [exercise (rf/subscribe [:workout/current-exercise])
          exercise-rem (rf/subscribe [:workout/exercise-remaining])
          is-active (rf/subscribe [:workout/exercise-active]) ]

      [rn/View {:style {:flex 1
                        :background-color (bg-color)
                        :justify-content "center" }}
       [rn/Text {:style {:text-align "center"}}
        (if @is-active
          (:description @exercise)
          [rn/Text {:style {:font-size 64}}
           (str "RESTING")])]])))

(defn- next-exercise []
  (fn []
    (let [next-ex (rf/subscribe [:workout/next-exercise])]
      [rn/View {:style {:background-color "cyan"}}
       [rn/Text {:style {:text-align "center"}}
        (str "Next: " (if @next-ex (:title @next-ex) "Done!"))]])))

(def main-panel
  (fn []
    [rn/View {:style {:flex 24
                      :flex-direction "column"
                      :background-color "ff503d"}}

     [rn/View {:style {:flex 1}}
      [workout-title]]

     [rn/View {:style {:flex 3}}
      [top-ex-panel]]

     [rn/View {:style {:flex 19}}
      [bottom-ex-panel]]

     [rn/View {:style {:flex 1}}
      [next-exercise]]

     ]))
