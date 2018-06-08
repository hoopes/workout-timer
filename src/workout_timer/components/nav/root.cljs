(ns workout-timer.components.nav.root
  (:require [reagent.core :as r]
            [workout-timer.nav.re-frame :as nav]
            [workout-timer.components.screens.workout.core :as w]
            [workout-timer.components.screens.config.core :as c]))

;; Our navigation is set up with a root stack navigator. This
;; allows us to have a main screen, and an overriding error screen,
;; if necessary.
;;
;; The main screen contains the main tab navigator. This is the screen
;; that people will deal with almost all of the time.

;; Do i need anything here?
(def tab-nav-opts {})

(def tab-defs
  {
   :ConfigTab  {:screen (nav/tab-screen c/main-stack c/tab-opts)}
   :WorkoutTab {:screen (nav/tab-screen w/main-stack w/tab-opts)}
   })

(def main-tab-nav
  (nav/tab-navigator tab-defs tab-nav-opts))

;; FIXME: Add global error screen

;; For the root stack, leave no header, taking up room. If any of
;; the sub-views want to have the header on, they can set it.
(def root-stack-opts
  {:headerMode "none"})

(def root-stack
  (nav/stack-navigator {:Main {:screen main-tab-nav}} root-stack-opts))

(defn routing-root []
  (r/create-class
    {:reagent-render
     (fn []
       [nav/router {:root-router root-stack}])}))
