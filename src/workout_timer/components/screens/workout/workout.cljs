(ns workout-timer.components.screens.workout.workout
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]))

;; This panel is shown when we have selected a workout

;; Pause button
;; display of current exercise
;; display current timer
;; switch workout button?
;; detect on-leave? we could stop the timer

(defn- temp-ex [ex]
  (fn []
    [rn/Text (:title ex)]))

(defn- timer-panel []
  (fn []
    [rn/View {:style {:flex 1 :flex-direction "row" :align-items "flex-start"}}
     [rn/View {:style {:flex 1 :height "100%"}}
      [rn/Text "TIME REMAINING"]]
     [rn/View {:style {:flex 1 :height "100%" :border-color "black" :border-width 1}}
      [rn/Text "TOTAL TIME"]]]))

(def workout-main-panel
  (fn []
    (let [workout (rf/subscribe [:workout/current-workout])]
      [rn/View {:style {:flex 1
                        :flex-direction "column"
                        :border-color "green"
                        :border-width 4 }}

       [rn/View {:style {:flex 2 :border-color "black" :border-width 1}}
        [timer-panel]]

       [rn/View {:style {:flex 9 :border-color "yellow" :border-width 1}}


        [rn/Text "WORKOUT IS HERE"]
        [rn/Text (:title @workout)]

        [rn/TouchableHighlight
         {:on-press (fn [] (println "ARE WE STARTING?"))}
         [rn/Text "START WORKOUT"]]

        ;; Note - using the idx as a key is bad practice, but I
        ;; don't really care here....
        (let [ex-list (rf/subscribe [:workout/exercise-list])]

          (map-indexed (fn [idx ex]
                         ^{:key idx} [temp-ex ex]) @ex-list))]

       ;(for [ex @ex-list]
       ;^{:key ex} [temp-ex ex]))]

       ])))

