(ns workout-timer.components.screens.workout.choose
  (:require [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]))

;; This panel is shown when we haven't selected a workout. It
;; should display a button to go to the config tab so the user
;; can select a workout to do.

(def choose-main-panel
  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack state]} navigation]
      [rn/View {:style {:flex 1
                        :flex-direction "column"
                        :border-color "blue"
                        :border-width 1 }}

       ;(println "IN MAIN PANEL")
       ;(println navigation)

       [rn/Text "CHOOSE A WORKOUT"]

       [rn/TouchableHighlight
        {
         ;:on-press (fn [] (println "BUTTON PRESSED"))
         ;:on-press #(navigate "ConfigTab")
         :on-press (fn []
                     (println navigate)
                     (navigate "ConfigTab"))
         }
        [rn/Text "OH MAN"]]])))

