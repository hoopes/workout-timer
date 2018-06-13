(ns workout-timer.components.screens.config.list.item
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.rn.core :as rn]))

(defn- title [workout]
  [rn/Text {:style {:text-align "center"}}
   (str "WORKOUT: " (:title workout))]
  )

(defn- edit-btn [workout]
  [rn/Button
   {:title "Edit"
    :on-press #(rf/dispatch [:edit/start workout])}])

(defn- run-btn [workout]
  [rn/Button
   {:title "Run"
    :on-press #(rf/dispatch [:workout/start-workout workout])}])

(defn- delete-btn [workout]
  [rn/Button
   {:title "Del"
    :on-press #(rf/dispatch [:workout/delete-workout [workout]])}])

(defn main [workout]
  (fn []
    [rn/View {:style {:flex-direction "row"
                      :border-color "blue"
                      :border-width 1 }}

     [rn/View {:style {:flex 8}} [title workout]]
     [rn/View {:style {:flex 2}} [run-btn workout]]
     [rn/View {:style {:flex 2}} [edit-btn workout]]
     [rn/View {:style {:flex 2}} [delete-btn workout]]

     ]))
