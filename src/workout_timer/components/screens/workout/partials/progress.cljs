(ns workout-timer.components.screens.workout.partials.progress
  (:require [re-frame.core :as rf]
            [workout-timer.rn.core :as rn]
            [goog.string :as gstr] ;; FIXME: hide in our own str lib
            [goog.string.format]))

(defn- to-min-sec [t]
  (let [min_ (int (/ t 60))
        sec_ (mod t 60)]
    (str min_ ":" (gstr/format "%02f" sec_))))

(defn- remaining-time []
  (let [remaining-time (rf/subscribe [:workout/overall-time-remaining])
        remaining-time-fmt (to-min-sec @remaining-time)
        overall-time (rf/subscribe [:workout/overall-time])
        overall-time-fmt (to-min-sec @overall-time)]
    [rn/View {:style {:justify-context "center"}}
     [rn/Text {:style {:text-align "center"
                       :text-align-vertical "center"
                       :height "100%"}}
      ;(str remaining-time-fmt " / " overall-time-fmt)]]))
      remaining-time-fmt]]))

(defn- exercise-progress []
  (let [ex-pos (rf/subscribe [:workout/exercise-position])
        ex-rem (rf/subscribe [:workout/num-exercises])]
    [rn/View {:style {:justify-content "center"}}
     [rn/Text {:style {:text-align "center"
                       :text-align-vertical "center"
                       :height "100%"}}
      (str @ex-pos "/" @ex-rem)]]))

(defn- progress-bar []
  (let [overall-time (rf/subscribe [:workout/overall-time])
        elapsed (rf/subscribe [:workout/elapsed])
        progress (/ @elapsed @overall-time)]
    [rn/View {:style {:justify-content "center"
                      :height "100%"
                      }}

     [rn/ProgressViewIOS {:style
                          {
                           ;:height 100
                           :height "100%"
                           ;:transform [{:scaleY 10}]
                           :transform [{:scaleY 4}]
                           }
                          :progress progress}]
     ]
    ))


(defn panel []
  (fn []
    [rn/View {:style {:flex 12
                      :flex-direction "row"
                      ;:border-color "red"
                      ;:border-width 3
                      }}

     [rn/View {:style {:flex 3}}

      [rn/View {:style {:flex 2
                        :flex-direction "column"
                        :border-color "black"
                        :border-right-width 2}}

       [rn/View {:style {:flex 1}}
        [remaining-time]]

       [rn/View {:style {:flex 1}}
        [exercise-progress]]]]

     [rn/View {:style {:flex 9}}
      [progress-bar]]
     ]))
