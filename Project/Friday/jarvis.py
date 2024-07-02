import openai
import speech_recognition as sr
import pyttsx3
import webbrowser
import datetime


# Set your OpenAI API key here
OPENAI_API_KEY = 'sk-proj-5gYfUtE1HNfdLiMatSLVT3BlbkFJJbR8NruyXRfYL0VZjVT0'

if not OPENAI_API_KEY or OPENAI_API_KEY == 'sk-proj-5gYfUtE1HNfdLiMatSLVT3BlbkFJJbR8NruyXRfYL0VZjVT0':
    raise ValueError("The OpenAI API key is missing or not set correctly in the script")

openai.api_key = OPENAI_API_KEY

engine = pyttsx3.init()
reminders = []

def get_gpt_response(prompt):
    messages = [
        {"role": "system", "content": "You are Friday, an AI engineering companion tool inspired by the AI from Iron Man. You help with various tasks and provide intelligent responses."},
        {"role": "user", "content": prompt}
    ]
    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=messages
        )
        return response['choices'][0]['message']['content']
    except openai.error.RateLimitError as e:
        print(f"Rate limit error: {e}")
        return "I'm currently experiencing a high volume of requests. Please try again later."
    except openai.error.OpenAIError as e:
        print(f"OpenAI error: {e}")
        return "There was an issue with the OpenAI service. Please try again later."
    except Exception as e:
        print(f"Unexpected error: {e}")
        return "An unexpected error occurred. Please try again later."

def recognize_speech():
    recognizer = sr.Recognizer()
    with sr.Microphone() as source:
        print("Listening...")
        audio = recognizer.listen(source)
        try:
            text = recognizer.recognize_google(audio)
            print(f"You said: {text}")
            return text
        except sr.UnknownValueError:
            print("Sorry, I did not understand that.")
        except sr.RequestError as e:
            print(f"Could not request results; {e}")
        return None

def speak(text):
    engine.say(text)
    engine.runAndWait()

def perform_web_search(query):
    url = f"https://www.google.com/search?q={query}"
    webbrowser.open(url)
    return f"I found some information for {query} online."

def add_reminder(task, time):
    reminder = {"task": task, "time": time}
    reminders.append(reminder)
    return f"Reminder added: {task} at {time}"

def check_reminders():
    current_time = datetime.datetime.now().strftime("%H:%M")
    for reminder in reminders:
        if reminder["time"] == current_time:
            speak(f"Reminder: {reminder['task']}")
            reminders.remove(reminder)

while True:
    check_reminders()
    user_input = recognize_speech()
    if user_input and user_input.lower() in ["exit", "quit"]:
        break
    if user_input:
        if "search for" in user_input.lower():
            query = user_input.lower().replace("search for", "").strip()
            response = perform_web_search(query)
        elif "remind me to" in user_input.lower():
            task = user_input.lower().replace("remind me to", "").strip()
            speak("At what time?")
            time_input = recognize_speech()
            if time_input:
                response = add_reminder(task, time_input)
            else:
                response = "I didn't get the time for the reminder."
        else:
            response = get_gpt_response(user_input)
        print("Friday: ", response)
        speak(response)
