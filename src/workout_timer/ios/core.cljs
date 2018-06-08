(ns workout-timer.ios.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ;[workout-timer.nav.re-frame :as nav]
            [day8.re-frame.http-fx]
            [day8.re-frame.async-flow-fx]
            [workout-timer.rn.core :as rn]
            [workout-timer.components.nav.root :as root-nav]
            [workout-timer.events.core]
            [workout-timer.subs.core]
            ;[workout-timer.utils.effects.core]
            ))

;(def root-component root-nav/root-stack)

;(defn app-root []
;;[:> root-component])
;(r/create-class
;{:react-router
;(fn []
;[nav/router {:root-router root-nav/root-stack}])}))

(def app-root root-nav/routing-root)
  ;(fn []
    ;[nav/router {:root-router root-nav/root-stack}]))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  ;(rf/dispatch-sync [:boot-app])
  ;(.registerComponent rn/AppRegistry "WorkoutTimer" root-component))
  (.registerComponent rn/AppRegistry "WorkoutTimer" (r/reactify-component root-nav/routing-root)))
