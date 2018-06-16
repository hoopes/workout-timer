(ns workout-timer.db.routing.core
  (:require [re-frame.core :as rf]
            [clojure.spec.alpha :as s]))

(def config-tab
  {:index     0,
   :routes    [{:routeName "ConfigList" :key "ConfigList" }
               ;{:routeName "ConfigWorkout" :key "ConfigWorkout" }
               ],
   :key       "ConfigTab",
   :routeName "ConfigTab"})

(def workout-tab
  {:index     0,
   :routes    [{:routeName "WorkoutMain" :key "WorkoutMain" }]
   :key       "WorkoutTab",
   :routeName "WorkoutTab"})

(def routing-state
  (clj->js {:index  0,
            :routes [{:routes    [config-tab workout-tab],
                      :index     0,
                      :key       "Main",
                      :routeName "Main"}
                     ]}))
