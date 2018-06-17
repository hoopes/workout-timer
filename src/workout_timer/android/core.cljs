(ns workout-timer.android.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]
            [workout-timer.components.root :as root]

            ;; Required here for their side effects...
            [day8.re-frame.http-fx]
            [day8.re-frame.async-flow-fx]
            [workout-timer.ops.core]
            [workout-timer.effects.core]
            ))

(def app-root root/router)

(defn init []
  (rf/dispatch-sync [:initialize-db])
  ;(rf/dispatch-sync [:boot-app])
  ;(.registerComponent rn/AppRegistry "WorkoutTimer" root-component))
  (.registerComponent rn/AppRegistry "WorkoutTimer" (r/reactify-component app-root)))
