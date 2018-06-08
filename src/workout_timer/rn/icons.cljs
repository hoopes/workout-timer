(ns workout-timer.rn.icons
  (:require [reagent.core :as r]))

(def MI  (js/require "react-native-vector-icons/MaterialIcons"))
(def MCI (js/require "react-native-vector-icons/MaterialCommunityIcons"))
(def FA  (js/require "react-native-vector-icons/FontAwesome"))
;(def II  (js/require "react-native-vector-icons/Ionicons"))


(def MaterialIcons (r/adapt-react-class (aget MI "default")))
(def MaterialCommunityIcons (r/adapt-react-class (aget MCI "default")))
(def FontAwesome (r/adapt-react-class (aget FA "default")))
;(def IonIcons (r/adapt-react-class (aget II "default")))
