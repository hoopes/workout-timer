(ns workout-timer.components.screens.config.workout.core
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.config.workout.util.events]
            [workout-timer.components.screens.config.workout.util.subs]
            [workout-timer.components.screens.config.workout.title :as title]
            [workout-timer.components.screens.config.workout.length :as length]
            [workout-timer.components.screens.config.workout.seconds-per-exercise :as sec]
            [workout-timer.components.screens.config.workout.exercises :as ex]
            [workout-timer.components.screens.config.workout.initial-rest :as init]
            [workout-timer.components.screens.config.workout.final-rest :as final]
            ))

;; This defines the root view of the "config" tab of
;; the main tab navigator

; just an example for later...
;[icons/MaterialIcons
;{:name "config" :size 26 :color "black"}]

(def nav-opts
  {:title "Configure Workout"})

(defn save-btn [navigate]
  (fn []
    [rn/Button
     {:title "SAVE"
      :on-press #(rf/dispatch [:edit/save-workout])}]))

(defn cancel-btn [navigate]
  (fn []
    [rn/Button
     {:title "CANCEL"
      :on-press #(rf/dispatch [:edit/cancel-workout-edit])}]))

(defn config-workout-screen []

  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate state]} navigation
          workout (get-in [:params :workout] state {})]

      (.log js/console "GOT WORKOUT")
      (.log js/console (clj->js workout))

      ;; This can't be the best place to do this, right?
      (rf/dispatch [:edit/set-workout workout])

      [rn/View {:style {:flex 1 :flex-direction "column" }}

       [rn/View {:style {:flex 1}} [title/main]]
       [rn/View {:style {:flex 1}} [length/main]]
       [rn/View {:style {:flex 1}} [sec/main]]

       [rn/View {:style {:flex 1}} [init/main]]
       [rn/View {:style {:flex 1}} [final/main]]

       ;; Exercise list
       ;[rn/View {:style {:flex 1}} [ex/main]]

       ;; Save/cancel buttons (save only enabled if edit workout set)
       [rn/View {:style {:flex 1 :flex-direction "row" }}
        [rn/View {:style {:flex 1}} [cancel-btn]]
        [rn/View {:style {:flex 1}} [save-btn]]]])))
