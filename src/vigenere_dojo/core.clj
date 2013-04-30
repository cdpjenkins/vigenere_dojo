(ns vigenere-dojo.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def table-line (vec (map char (range (int \A) (inc (int \Z))))))

(defn rotate [line i]
  (concat (drop i line) (take i line)))

(def table-whole (vec (map (partial rotate table-line) (range 0 26))))

(defn char-to-int [c]
  (-  (int c) (int \A)))

(defn encode-pair [pt-char key-char]
  (nth (nth table-whole (char-to-int key-char))
       (char-to-int pt-char)))

(defn encrypt [plain-text key-word]
  (apply str (map encode-pair plain-text (cycle key-word))))

(defn decode-pair [pt-char key-char]
  (let [row (nth table-whole key-char)
        i (.indexOf row pt-char)]
    (nth table-line i)))


(defn decrypt [ct k]
  (apply str (map decode-pair
                  ct
                  (map char-to-int (cycle k)))))

(defn test-1 []
  (let [pt "ATTACKATDAWN"
        key "LEMON"
        ct "LXFOPVEFRNHR"
        ]
    (and
     (= (encrypt pt key) ct)
     (= (decrypt ct key) pt))))
