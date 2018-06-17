(ns workout-timer.db.core
  (:require [clojure.spec.alpha :as s]
            [workout-timer.db.exercises.core :as e]
            [workout-timer.db.routing.core :as r]))

;; FIXME: Spec an exercise
;; FIXME: Spec a workout
;; FIXME: Spec the current exercise, including timing info

;; initial state of app-db
(def app-db
  {
   :app-initialized false

   ;; The map of id to exercise for all available exercises
   :exercises (zipmap (map :id e/exercises) e/exercises)

   ;; The id of the current exercise chosen
   :current-exercise nil

   ;; A map of id to workout map
   :workouts {}

   ;; The id of the current workout?
   :current-workout nil

   ;; The routing info - required to be at this key at the moment,
   ;; see the nav routing stuff...
   :routing r/routing-state
   })
