from google import genai
from config import GEMINI_API_KEY

client = genai.Client(api_key=GEMINI_API_KEY)

async def ask_agent(question: str) -> str:
    response = client.models.generate_content(
        model="gemini-3-flash-preview",
        contents=f"{question}. Answer in 5 sentences max. No markdown formatting, plain text only."
    )
    return response.text