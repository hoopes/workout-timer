(ns workout-timer.components.screens.workout.choose
  (:require [re-frame.core :as rf]
    [workout-timer.nav.re-frame :as nav]
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

       [rn/TouchableHighlight
        {
         ;:on-press (fn [] (println "BUTTON PRESSED"))
         ;:on-press #(navigate "ConfigTab")
         :on-press #(rf/dispatch [::nav/navigate "ConfigTab"])
         }

        [rn/Text "Choose a workout"]]])))

