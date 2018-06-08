(ns workout-timer.components.screens.workout.workout
  (:require [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]))

;; This panel is shown when we have selected a workout

;; Pause button
;; display of current exercise
;; display current timer
;; switch workout button?
;; detect on-leave? we could stop the timer

(def workout-main-panel
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack]} navigation]
      [rn/View {:style {:flex 1
                        :flex-direction "column"
                        :border-color "green"
                        :border-width 4 }}

       [rn/Text "WORKOUT IS HERE"]

       [rn/TouchableHighlight
        {:on-press (fn [] (println "ARE WE STARTING?"))}
        [rn/Text "START WORKOUT"]]])))

