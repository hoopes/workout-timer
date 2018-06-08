(ns workout-timer.components.screens.config.workout.seconds-per-exercise
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(def picker-name :seconds-per-exercise)

(defn- label []
  [rn/Text
   {:style {:font-weight "bold" :font-size 20}}
   "Seconds per exercise: "])

(defn- btn []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Button
     {:title (str (:seconds-per-exercise @workout))
      :on-press #(rf/dispatch [:edit/set-picker picker-name])}]))

;; FIXME: Add cancel button
(defn- picker []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Modal {:animation-type "slide"}
     [rn/View {:style {:flex 1 :justify-content "center"}}
      [rn/Picker
       {:selected-value (:seconds-per-exercise @workout)
        :on-value-change #(doall (map rf/dispatch
                                      [[:edit/set-workout-attr :seconds-per-exercise %]
                                       [:edit/close-picker]]))}

       ;; FIXME: Make this a loop?
       [rn/Picker.Item {:label "30" :value 30}]
       [rn/Picker.Item {:label "40" :value 40}]
       [rn/Picker.Item {:label "50" :value 50}]
       [rn/Picker.Item {:label "60" :value 60}]]]]))

(defn main []
  "

  "
  (fn []
    (let [curr-picker (rf/subscribe [:edit/open-picker])
          picker-open (= @curr-picker picker-name)
          workout     (rf/subscribe [:edit/workout])]

      [rn/View {:style {:flex 1 :flex-direction "column"}}

       [rn/View {:style {:flex 1 :flex-direction "row"}}
        [rn/View {:style {:flex 4}} [label]]
        [rn/View {:style {:flex 1}} [btn]]]

       (when picker-open
         [rn/View {:style {:flex 3}} [picker]])])))
