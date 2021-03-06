(ns workout-timer.components.screens.config.workout.length
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(def picker-name :overall-length)

(defn- label []
  [rn/Text
   {:style {:font-weight "bold" :font-size 20}}
   "Overall workout length: "])

(defn- btn []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Button
     {:title (str (:total-length @workout))
      :on-press #(rf/dispatch [:edit/set-picker picker-name])}]))

;; FIXME: Add cancel button
(defn- picker []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Modal {:animation-type "slide"}
     [rn/View {:style {:flex 1 :justify-content "center"}}
      [rn/Picker
       {:selected-value (:total-length @workout)
        :on-value-change #(doall (map rf/dispatch
                                      [[:edit/set-workout-attr :total-length %]
                                       [:edit/close-picker]]))}

       ;; FIXME: Make this a loop?
       [rn/Picker.Item {:label "5"  :value 5}]
       [rn/Picker.Item {:label "10" :value 10}]
       [rn/Picker.Item {:label "15" :value 15}]
       [rn/Picker.Item {:label "20" :value 20}]
       [rn/Picker.Item {:label "25" :value 25}]
       [rn/Picker.Item {:label "30" :value 30}]]]]))

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
