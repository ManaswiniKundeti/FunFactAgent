from fastapi import APIRouter
from fastapi.responses import PlainTextResponse
from agent import ask_agent

router = APIRouter()

@router.get("/agent/fact", response_class=PlainTextResponse)
async def get_fact(question: str = "Give me one interesting space fact in 2 sentences."):
    return await ask_agent(question)