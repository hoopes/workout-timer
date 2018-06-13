(ns workout-timer.components.screens.workout.core
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.workout.choose :as choose]
            [workout-timer.components.screens.workout.workout :as workout]))

;; This defines the root view of the "workout" tab of
;; the main tab navigator

(defn tab-icon [{:keys [tintColor focused] :as props}]
  [icons/MaterialCommunityIcons
   {:name "heart-outline"
    :size 26
    :color (if focused
             s/tab-focused-color
             s/tab-unfocused-color)}])

(def tab-opts
  {:title "Workout"
   :tabBarIcon tab-icon})

(def root-view-style {:flex 1
                      :flex-direction "column"
                      :border-color "red"
                      :border-width 4 })

(defn workout-main-screen []

  (fn []
    (let [workout (rf/subscribe [:current-workout])]

      [rn/View {:style root-view-style}

       (if @workout
         [workout/workout-main-panel]
         [choose/choose-main-panel])])))


(def stack-screens
  {:WorkoutMain {:screen (nav/stack-screen workout-main-screen {})}})

(def stack-opts
  {:headerMode "none"})

(def main-stack
  (nav/stack-navigator stack-screens stack-opts))
