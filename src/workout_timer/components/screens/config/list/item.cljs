(ns workout-timer.components.screens.config.list.item
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(defn- title [workout]
  [rn/Text {:style {:text-align "center"}}
   (str "WORKOUT: " (:title workout))]
  )

(defn- edit-btn [props workout]
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate]} navigation]
      [rn/Button
       {:title "Edit"
        :on-press #(rf/dispatch [:workout/set-workout workout])}])))

(defn- run-btn [props workout]
  [rn/Button
   {:title "Run"
    :on-press #(println "RUNNING!")}])

(defn main [props workout]
  (fn []
    [rn/View {:style {:flex-direction "row"
                      :border-color "blue"
                      :border-width 1 }}

     [rn/View {:style {:flex 8}} [title workout]]
     [rn/View {:style {:flex 2}} [edit-btn props workout]]
     [rn/View {:style {:flex 2}} [run-btn props workout]]

     ]))
