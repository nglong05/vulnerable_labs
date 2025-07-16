FROM python:3.10-slim
WORKDIR /app

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt

COPY real_main.py ./
RUN mv real_main.py main.py

COPY utils/ ./utils/
COPY 6C3n6DvHRC2Q/ ./6C3n6DvHRC2Q/

EXPOSE 8008

CMD ["python3", "main.py"]


