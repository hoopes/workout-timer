(ns workout-timer.db.exercises.core
  (:require [workout-timer.db.exercises.strength :as strength]
            [workout-timer.db.exercises.body_core :as core]
            [workout-timer.db.exercises.cardio :as cardio]))

(def exercises [])

  ;(into []
        ;(concat strength/exercises
                ;core/exercises
                ;cardio/exercises)))
