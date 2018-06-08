(ns workout-timer.components.screens.config.workout.title
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(defn- label []
  [rn/Text
   {:style {:font-weight "bold" :font-size 20}}
   "Title: "])

(defn- input []
  [rn/TextInput
   {:style {:text-align "center" }
    :placeholder "Enter Title"
    :on-change-text #(rf/dispatch [:edit/set-workout-attr :title %])}])

(defn main []

  (fn []
    [rn/View {:style {:flex 1 :flex-direction "row"}}
     [rn/View {:style {:flex 4}} [label]]
     [rn/View {:style {:flex 4}} [input]]]))
