(ns workout-timer.rn.core
  (:require [reagent.core :as r]))

(defonce ReactNative (js/require "react-native"))
(defonce AppRegistry (.-AppRegistry ReactNative))
(defonce NativeModules (.-NativeModules ReactNative))
(defonce ReactNavigation (js/require "react-navigation"))

(defonce TimeFrameModule (js/require "react-timeframe"))
(defonce TimeFrame
  (r/adapt-react-class (get (js->clj TimeFrameModule) "default")))

(defonce AsyncStorage (.-AsyncStorage ReactNative))

; Why do i not use adapt-react-class here?
(defonce Alert (.-Alert ReactNative))
(defonce ActivityIndicator (.-ActivityIndicator ReactNative))

;(defonce Picker (r/adapt-react-class (.-Picker ReactNative)))
;(defonce PickerItem (r/adapt-react-class (.-Item Picker)))

(defonce Animated ^ReactNative.Animated (.-Animated ReactNative))
(defonce AnimatedValue ^ReactNative.Animated.Value (.-Value Animated))
(defonce AnimatedImage (.-AnimatedImage ReactNative))

;; WTF is with this? Uncommenting causes an error
;(defonce DataSource (r/adapt-react-class (.-DataSource ListView)))

;; react-native
(defonce Button (r/adapt-react-class (.-Button ReactNative)))
(defonce DatePickerIOS (r/adapt-react-class (.-DatePickerIOS ReactNative)))
(defonce Dimensions (r/adapt-react-class (.-Dimensions ReactNative)))
(defonce FlatList (r/adapt-react-class (.-FlatList ReactNative)))
(defonce Image (r/adapt-react-class (.-Image ReactNative)))
(defonce InteractionManager (r/adapt-react-class (.-InteractionManager ReactNative)))
(defonce Keyboard (r/adapt-react-class (.-Keyboard ReactNative)))
(defonce KeyboardAvoidingView (r/adapt-react-class (.-KeyboardAvoidingView ReactNative)))
(defonce LayoutAnimation (r/adapt-react-class (.-LayoutAnimation ReactNative)))
(defonce Linking (r/adapt-react-class (.-Linking ReactNative)))
(defonce Modal (r/adapt-react-class (.-Modal ReactNative)))
(defonce Picker (r/adapt-react-class (.-Picker ReactNative)))
(defonce PickerIOS (r/adapt-react-class (.-PickerIOS ReactNative)))
(defonce Picker.Item (r/adapt-react-class (.-Picker.Item ReactNative)))
(defonce Platform (r/adapt-react-class (.-Platform ReactNative)))
(defonce ScrollView (r/adapt-react-class (.-ScrollView ReactNative)))
(defonce Slider (r/adapt-react-class (.-Slider ReactNative)))
(defonce Text (r/adapt-react-class (.-Text ReactNative)))
(defonce TextInput (r/adapt-react-class (.-TextInput ReactNative)))
(defonce TouchableHighlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(defonce TouchableOpacity (r/adapt-react-class (.-TouchableOpacity ReactNative)))
(defonce View (r/adapt-react-class (.-View ReactNative)))
(defonce VirtualizedList (r/adapt-react-class (.-VirtualizedList ReactNative)))

(defn alert [title]
  (.alert (.-Alert ReactNative) title))

