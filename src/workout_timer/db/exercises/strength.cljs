(ns workout-timer.db.exercises.strength)

(def exercises
  [

   ;; Strength exercises
   {:id :hand-release-push-ups
    :title "Hand Release Push Ups"
    :description (str "Start in a standard push-up position, and drop your "
                      "body all the way down to the floor. Lift your hands "
                      "off the ground for a second, then exhale while you "
                      "press your body all the way back up. If you need to "
                      "modify this move, just drop to your knees.")
    :category :strength}

   {:id :plyo-push-ups
    :title "Plyo Push-Ups"
    :description (str "Start in push-up position, and lower your body to the "
                 "floor. Get a big push off the floor and lift your hands off "
                 "the ground before landing back in push-up position. To "
                 "modify, drop to your knees.")
    :category :strength}

   {:id :push-ups
    :title "Push-ups"
    :description (str "Body weight push ups.")
    :category :strength}

   {:id :twisting-push-ups
    :title "Twisting Push-ups"
    :description (str "Body weight push ups, twist at the top.")
    :category :strength}

   {:id :squats
    :title "Squats"
    :description (str "Body weight squats")
    :category :strength}

   {:id :curls
    :title "Curls"
    :description (str "Dumbbell Curls")
    :category :strength}

   {:id :shoulder-presses
    :title "Shoulder presses"
    :description (str "Dumbbell shoulder presses")
    :category :strength}

   {:id :dumbbell-rows
    :title "Dumbbell rows"
    :description (str "Dumbbell shoulder rows")
    :category :strength}

   {:id :resistance-band-pulldowns
    :title "Resistance Band Pulldowns"
    :description (str "")
    :category :strength}

   ])
