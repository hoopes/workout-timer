(ns workout-timer.components.screens.config.core
  (:require [workout-timer.nav.reagent :as nav]
            [workout-timer.styles.core :as s]
            [workout-timer.rn.core :as rn]
            [workout-timer.rn.icons :as icons]
            [workout-timer.components.screens.config.list.core :as l]
            [workout-timer.components.screens.config.workout.core :as w]))

;; This defines the root view of the "config" tab of
;; the main tab navigator - we also define some of the tab options
;; here, including the tab icon

(defn tab-icon [{:keys [tintColor focused] :as props}]
  [icons/MaterialCommunityIcons
   {:name "menu"
    :size 26
    :color (if focused s/tab-focused-color s/tab-unfocused-color)}])

(def tab-opts
  {:title "Config"
   :tabBarIcon tab-icon})

(def stack-screens
  {:ConfigList    {:screen (nav/stack-screen l/list-main-screen l/nav-opts)}
   :ConfigWorkout {:screen (nav/stack-screen w/config-workout-screen w/nav-opts)}})

; Empty for now...
(def stack-opts {})

(def main-stack
  (nav/stack-navigator stack-screens stack-opts))
