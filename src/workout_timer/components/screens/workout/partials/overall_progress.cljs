(ns workout-timer.components.screens.workout.partials.overall-progress
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]
            [goog.string :as gstr] ;; FIXME: hide in our own str lib
            [goog.string.format]))

(defn- to-min-sec [t]
  (let [min_ (int (/ t 60))
        sec_ (mod t 60)]
    (str min_ ":" (gstr/format "%02f" sec_))))


(defn panel []
  (fn []
    (let [overall-time (rf/subscribe [:workout/overall-time])
          remaining-time (rf/subscribe [:workout/overall-time-remaining])
          overall-time-fmt (to-min-sec @overall-time)
          remaining-time-fmt (to-min-sec @remaining-time)]
      [rn/View {:style {:flex 1 :flex-direction "row" :align-items "flex-start"}}
       [rn/View {:style {:flex 1 :height "100%"}}
        [rn/Text (str "TIME REMAINING: " remaining-time-fmt)]]
       [rn/View {:style {:flex 1 :height "100%" :border-color "black" :border-width 1}}
        [rn/Text (str "TOTAL TIME: " overall-time-fmt)]]])))
