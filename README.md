# FunFact Agent 🚀

A full-stack learning project: Android app powered by a FastAPI backend with a Gemini AI agent.

Tap a button → backend calls Gemini → space fact appears. Simple, end-to-end.

## Architecture
```
Android (Compose) → FastAPI BFF → Gemini AI Agent → response → UI
```

## Stack

- **Android**: Kotlin + Jetpack Compose + OkHttp
- **Backend**: Python + FastAPI + Google Gemini (`google-genai`)

## Project Structure
```
FunFactAgent/
├── backend/          # FastAPI + Gemini
│   ├── main.py
│   └── venv/
└── AndroidApp/       # Jetpack Compose app
```

## Running Locally

**Backend**
```bash
cd backend
source venv/bin/activate
uvicorn main:app --host 0.0.0.0 --port 8000 --reload
```

**Android**
- Run on emulator (uses `10.0.2.2:8000` to reach localhost)
- Requires `INTERNET` permission in manifest

## What I Learned

- BFF (Backend for Frontend) pattern
- Calling an AI agent from a mobile app
- FastAPI basics: routes, responses
- Python virtual environments + keeping secrets out of code
- Jetpack Compose state management with network calls

## Next Steps

- [x] User types their own question (dynamic input)
- [ ] Streaming response (word by word)
- [ ] Deploy backend to GCP Cloud Run