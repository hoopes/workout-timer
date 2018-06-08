(ns workout-timer.components.screens.config.workout.exercises
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(defn- label []
  [rn/Text
   {:style {:font-weight "bold" :font-size 20}}
   "Exercises:"])

(defn- exercise-chooser []

  )

(defn- no-exercises []
  (fn []
    [rn/View {:style {:flex 1 :justify-content "center"}}
     [rn/Text
      {:style {:font-weight "bold"
               :font-size 20
               :text-align "center"}}
      "No Exercises Yet..."]]))

(defn- exercise-list []
  (let [workout (rf/subscribe [:edit/workout])]

    [rn/View {:style {:flex 1 :justify-content "center"}}
     [rn/Text
      {:style {:font-weight "bold"
               :font-size 20
               :text-align "center"}}
      "OMG LOOK AT EM"]]))

(defn main []
  "

  "
  (fn []
    (let [workout       (rf/subscribe [:edit/workout])
          has-exercises (rf/subscribe [:edit/has-exercises])
          picker-open   (rf/subscribe [:edit/exercise-picker-open])]

      [rn/View {:style {:flex 1 :flex-direction "column"}}

       [rn/View {:style {:flex 4}} [label]]

       ;[rn/View {:style {:flex 4}} [no-exercises]]
       ;[rn/View {:style {:flex 4}} [exercise-list]]

       [rn/View {:style {:flex 4}}
        (if @has-exercises
          [exercise-list]
          [no-exercises])]

       ])))
