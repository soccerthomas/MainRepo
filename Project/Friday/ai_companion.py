import openai
import webbrowser
import datetime
import tkinter as tk
import cv2
# Set your OpenAI API key here
#Friday Mark 1 7/6/2024
openai.api_key = 
reminders = []

def get_gpt_response(prompt, context=[]):
    messages = [
        {"role": "system", "content": "You are Friday, an AI engineering companion tool inspired by the AI from Iron Man. You help with various tasks and provide intelligent responses."}
    ] + context + [{"role": "user", "content": prompt}]
    
    try:
        response = openai.ChatCompletion.create(
            model="gpt-4",
            messages=messages
        )
        return response['choices'][0]['message']['content']
    except Exception as e:
        print(f"An error occurred: {e}")
        return "An error occurred. Please try again later."

def perform_web_search(query):
    url = f"https://www.google.com/search?q={query}"
    webbrowser.open(url)
    return f"Friday: I found some information for {query} online."

def add_reminder(task, time):
    reminder = {"task": task, "time": time}
    reminders.append(reminder)
    return f"Friday: Reminder added - {task} at {time}"

def check_reminders():
    current_time = datetime.datetime.now().strftime("%H:%M")
    for reminder in reminders:
        if reminder["time"] == current_time:
            print(f"Friday: Reminder - {reminder['task']}")
            reminders.remove(reminder)

def capture_image():
    cap = cv2.VideoCapture(0)
    ret, frame = cap.read()
    if ret:
        cv2.imshow('frame', frame)
        cv2.imwrite('capture.jpg', frame)
    cap.release()
    cv2.destroyAllWindows()
    return "Friday: Image captured."

def process_image():
    try:
        img = cv2.imread('capture.jpg')  # Load the captured image
        if img is None:
            return "Friday: No image found to process."
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)  # Convert to grayscale
        cv2.imshow('Gray Image', gray)  # Display the grayscale image
        cv2.imwrite('gray_capture.jpg', gray)  # Save the processed image
        cv2.waitKey(0)  # Wait for a key press
        cv2.destroyAllWindows()  # Close the window
        return "Friday: Image processed and saved as grayscale."
    except Exception as e:
        return f"Friday: An error occurred while processing the image - {e}"

def create_gui():
    root = tk.Tk()
    root.title("AI Companion Tool - Friday")

    def on_submit():
        user_input = entry.get()
        if user_input:
            if "search for" in user_input.lower():
                query = user_input.lower().replace("search for", "").strip()
                response = perform_web_search(query)
            elif "remind me to" in user_input.lower():
                task = user_input.lower().replace("remind me to", "").strip()
                response = add_reminder(task, datetime.datetime.now().strftime("%H:%M"))
            elif "capture image" in user_input.lower():
                response = capture_image()
            elif "process image" in user_input.lower():
                response = process_image()
            else:
                response = get_gpt_response(user_input)
            response_label.config(text=response)

    entry = tk.Entry(root)
    entry.pack()

    submit_button = tk.Button(root, text="Submit", command=on_submit)
    submit_button.pack()

    response_label = tk.Label(root, text="")
    response_label.pack()

    root.mainloop()

if __name__ == "__main__":
    create_gui()
