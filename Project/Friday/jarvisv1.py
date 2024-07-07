import openai
import speech_recognition as sr
import pyttsx3
import config

# Initialize OpenAI GPT using the API key from config.py
openai.api_key = config.API_KEY

# Initialize the text-to-speech engine
tts_engine = pyttsx3.init()

# Function to recognize speech from the microphone
def recognize_speech_from_mic():
    recognizer = sr.Recognizer()
    with sr.Microphone() as source:
        print("Listening...")
        audio = recognizer.listen(source)
        try:
            return recognizer.recognize_google(audio)
        except sr.UnknownValueError:
            print("Sorry, I did not understand that.")
            return None
        except sr.RequestError:
            print("Could not request results from Google Speech Recognition service.")
            return None

# Function to get response from GPT-3.5 Turbo
def get_gpt_response(prompt):
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": prompt},
        ],
    )
    return response.choices[0].message['content'].strip()

# Function to convert text to speech and play it
def text_to_speech(text):
    tts_engine.say(text)
    tts_engine.runAndWait()

# Main function to run the helmet AI
def main():
    print("Iron Man Helmet AI Activated.")
    while True:
        command = recognize_speech_from_mic()
        if command:
            print(f"You said: {command}")
            response = get_gpt_response(command)
            print(f"GPT-3.5 Turbo says: {response}")
            text_to_speech(response)

if __name__ == "__main__":
    main()
