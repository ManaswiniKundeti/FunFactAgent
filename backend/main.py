from fastapi import FastAPI
from routes import router

app = FastAPI()
app.include_router(router)

# app = FastAPI()

# client = genai.Client(api_key="AIzaSyDEr-duA1JP5YMdeiRMsz5aMPupGifOSfA")

# @app.get("/agent/fact", response_class=PlainTextResponse)
# async def get_fact():
#     response = client.models.generate_content(
#         model="gemini-3-flash-preview",
#         contents="You are a helpful assistant. Give me one interesting space fact. Keep it to 2 sentences."
#     )
#     return response.text