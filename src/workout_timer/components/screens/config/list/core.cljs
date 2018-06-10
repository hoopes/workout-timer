(ns workout-timer.components.screens.config.list.core
  (:require [re-frame.core :as rf]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.config.list.list :as l]))

;; This screen simply lists the workouts that you have saved,
;; and allows you to add a new one.

;; The navigation opts for the list view
(def nav-opts
  {:headerTitle "Workouts"})

;; This view is displayed when there are no workouts to choose from,
;; and we just wanna prompt the user to create one
(defn no-workouts-view [props]
  [rn/View {:style {:flex 1
                    :flex-direction "column"
                    :justify-content "center"}}

   [rn/Text {:style {:text-align "center"}}
    "You have no workouts, add one!"]])

(defn add-workout-btn [navigate]
  (fn []
    [rn/Button
     {:title "ADD WORKOUT"
      :on-press #(rf/dispatch [:edit/start])}]))

(defn list-main-screen []

  (fn [{:keys [screenProps navigation] :as props}]
    (let [{:keys [navigate goBack]} navigation
          workout-list (rf/subscribe [:ordered-workout-list])]

      [rn/View {:style {:flex 1 :flex-direction "column" }}

       [rn/View {:style {:flex 9}}
        (if (empty? @workout-list)
          [no-workouts-view]
          [l/workout-list-view props])]

       [rn/View {:style {:flex 1}}
        [add-workout-btn navigate]]])))
