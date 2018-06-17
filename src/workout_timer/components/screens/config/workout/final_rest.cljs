(ns workout-timer.components.screens.config.workout.final-rest
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(def picker-name :final-rest)

(defn- label []
  [rn/Text
   {:style {:font-weight "bold" :font-size 20}}
   "Final Rest:"])

(defn- btn []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Button
     {:title (str (:final-rest @workout))
      :on-press #(rf/dispatch [:edit/set-picker picker-name])}]))

;; FIXME: Add cancel button
;; FIXME: Create utility component for this
(defn- picker []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Modal {:animation-type "slide"}
     [rn/View {:style {:flex 1 :justify-content "center"}}
      [rn/Picker
       {:selected-value (:final-rest @workout)
        :on-value-change #(doall (map rf/dispatch
                                      [[:edit/set-workout-attr :final-rest %]
                                       [:edit/close-picker]]))}

       ;; FIXME: Make this a loop?
       [rn/Picker.Item {:label "40" :value 40}]
       [rn/Picker.Item {:label "45" :value 45}]
       [rn/Picker.Item {:label "50" :value 50}]]]]))

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
