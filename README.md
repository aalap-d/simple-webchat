# 💬 Simple Java Web Chat

Welcome to the Simple Java Web Chat! This is a lightweight chat application that lets you create private rooms and chat in real-time. 

You can run this project in two ways:
1. **Locally:** Just for you, to test it out on your own computer.
2. **Over the Internet (Ngrok):** To generate a public link so your friends can join your chat rooms from anywhere.

---

## 🛠️ What You Need First (Prerequisites)
Before you start, make sure you have the following ready:
* **Java** installed on your computer.
* **Command Prompt** or **PowerShell** (Terminal) open in the folder where these files are saved.

---

## 💻 Method 1: Running Locally (Testing by yourself)

Use this method if you just want to see how the chat looks and works on your own screen.

**Step 1: Compile the Code**
Tell your computer to translate the Java code into a runnable program. Type this into your terminal and press Enter:
```bash
javac WebServer.java
```

**Step 2: Start the Server**
Now, turn the server on by running this command:
```bash
java WebServer
```

**Step 3: Open the Chat**
Open your favorite web browser and navigate to:
http://localhost:9001


## 🌍 Method 2: Chatting with Friends (Using Ngrok)
**Part A: One-Time Setup**
Go to ngrok.com and create a free account.
Download and extract ngrok.exe into your project folder.
Open your terminal in your chat folder and run this command (replace the token with your own):
```bash
.\ngrok config add-authtoken YOUR_TOKEN_HERE
```

**Part B: Going Live**
To do this, you need two separate terminal windows open at the same time.
Terminal 1: Start the Chat Server
```bash
java WebServer
```

Terminal 2: Start the Internet Tunnel
Open a new terminal window in the same folder and run:
```bash
.\ngrok http 9001
```

**Part C: Inviting Friends**
Look at Terminal 2 for the Forwarding line.
Copy the web link (e.g., https://random-words.ngrok-free.app).
Send this link to your friends!

---
