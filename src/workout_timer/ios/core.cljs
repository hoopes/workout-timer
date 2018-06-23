(ns workout-timer.ios.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]
            [workout-timer.components.root :as root]

            ;; Required here for their side effects
            [day8.re-frame.http-fx]
            [day8.re-frame.async-flow-fx]
            [district0x.re-frame.interval-fx]
            [workout-timer.effects.core]
            [workout-timer.ops.core]))

(def app-root root/router)

(defn init []
  (rf/dispatch-sync [:boot/initialize-db])
  (rf/dispatch-sync [:boot/boot-app])
  (.registerComponent rn/AppRegistry "WorkoutTimer" (r/reactify-component app-root)))
