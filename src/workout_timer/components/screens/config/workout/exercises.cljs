(ns workout-timer.components.screens.config.workout.exercises
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]))

(def picker-name :num-exercises)

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

(defn- btn []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Button
     {:title (str (:num-exercises @workout))
      :on-press #(rf/dispatch [:edit/set-picker picker-name])}]))

(defn- picker []
  (let [workout (rf/subscribe [:edit/workout])]
    [rn/Modal {:animation-type "slide"}
     [rn/View {:style {:flex 1 :justify-content "center"}}
      [rn/Picker
       {:selected-value (:num-workouts @workout)
        :on-value-change #(doall (map rf/dispatch
                                      [[:edit/set-workout-attr :num-exercises %]
                                       [:edit/close-picker]]))}

       ;; FIXME: Make this a loop?
       [rn/Picker.Item {:label "10" :value 10}]
       [rn/Picker.Item {:label "12" :value 12}]
       [rn/Picker.Item {:label "15" :value 15}]
       [rn/Picker.Item {:label "18" :value 18}]
       [rn/Picker.Item {:label "20" :value 20}]]]]))

(defn main []
  "

  "
  (fn []
    (let [curr-picker (rf/subscribe [:edit/open-picker])
          picker-open (= @curr-picker picker-name)]

      [rn/View {:style {:flex 1 :flex-direction "column"}}

       [rn/View {:style {:flex 1 :flex-direction "row"}}
        [rn/View {:style {:flex 4}} [label]]
        [rn/View {:style {:flex 1}} [btn]]]

       ;[rn/View {:style {:flex 4}}
        ;(if @has-exercises
          ;[exercise-list]
          ;[no-exercises])]

       (when picker-open
         [rn/View {:style {:flex 3}} [picker]])

       ])))
