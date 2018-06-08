(ns workout-timer.android.core
  (:require [re-frame.core :as rf]
            [day8.re-frame.http-fx]
            [day8.re-frame.async-flow-fx]
            [workout-timer.rn.core :as rn]
            [workout-timer.components.nav.root :as root-nav]
            [workout-timer.events.core]
            [workout-timer.subs.core]
            ;[workout-timer.utils.effects.core]
            ))

(def root-component root-nav/root-stack)

(defn app-root []
  [:> root-component])

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (rf/dispatch-sync [:boot-app])
  (.registerComponent rn/AppRegistry "WorkoutTimer" root-component))
